import { Link } from "react-router-dom";
import Header from "../components/Header";
import Sidebar from "../components/SideBar";
import Comment from "../components/Comment";
import { useEffect, useState } from "react";

import axios from "axios";
import Cookies from 'js-cookie';

interface Post {
  id: number;
  owner: string;
  header: string;
  content: string;
  votes: number;
  createdAt: Date;
}

// Mock data for testing
const mockPosts: Post[] = [
  {
    id: 1,
    owner: "john_doe",
    header: "First Post",
    content: "This is my first post on SpringOrbit! Really excited to share my thoughts here.",
    votes: 42,
    createdAt: new Date("2024-03-15T10:30:00")
  },
  {
    id: 2,
    owner: "john_doe",
    header: "Learning Spring Boot",
    content: "Just finished building my first microservice with Spring Boot. The journey has been amazing!",
    votes: 28,
    createdAt: new Date("2024-03-14T15:45:00")
  },
  {
    id: 3,
    owner: "john_doe",
    header: "React + Spring Boot",
    content: "Here's how I integrated my React frontend with Spring Boot backend. #webdev #java #react",
    votes: 35,
    createdAt: new Date("2024-03-13T09:20:00")
  }
];

const Profile: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffect(() => {
    
  
    
   
    const fetchPosts = async () => {
      try {
        const userId = Cookies.get('user');
        if (!userId) {
          console.error('No user ID found');
          return;
        }
        const response = await axios.get(`http://localhost:8084/POST-SERVICE/post/user/${userId}`);
        console.log(`http://localhost:8084/POST-SERVICE/post/user/${userId}`)
        setPosts(response.data)
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    };

    fetchPosts();
    
  }, []);

  return <div className="w-screen h-screen max-h-screen flex flex-col">
    <Header />
    {/* This div takes the remaining height after the header */}
    <div className="flex-1 min-h-0  flex w-full">
      {/* Sidebar - Takes the same height as the parent and allows scrolling */}
        <Sidebar />
      <div className="w-3/5 h-full px-10 py-6 flex flex-col overflow-scroll ">
            <div className="w-full flex flex-col h-60 min-h-60 border-b  border-[#00000022] ">
                <div className="w-full  flex h-4/5  items-center gap-5">
                    <div className="w-fit relative h-fit  ">
                        <Link to="/profile/edit" className="absolute bottom-0 right-0 w-10 h-10 rounded-full bg-[#dddddd] z-1 flex justify-center items-center">
                        <svg  aria-hidden="true" fill="currentColor" height="16" icon-name="add-media-outline" viewBox="0 0 20 20" width="16" xmlns="http://www.w3.org/2000/svg">
                                <path d="M17.376 3.032h-2.355L13.8 1.446A1.155 1.155 0 0 0 12.892 1h-5.74a1.17 1.17 0 0 0-.923.454L5.014 3.031H2.625A2.629 2.629 0 0 0 0 5.656v9.719A2.63 2.63 0 0 0 2.625 18h14.75A2.63 2.63 0 0 0 20 15.375V5.657a2.627 2.627 0 0 0-2.624-2.625Zm1.374 12.343a1.377 1.377 0 0 1-1.375 1.375H2.625a1.377 1.377 0 0 1-1.375-1.375V5.656a1.377 1.377 0 0 1 1.375-1.375h3L7.152 2.25l5.657-.041 1.6 2.072h2.971a1.375 1.375 0 0 1 1.37 1.376v9.718Zm-8.125-6H14v1.25h-3.375V14h-1.25v-3.375H6v-1.25h3.375V6h1.25v3.375Z"></path></svg>
                        </Link>
                            <img src="" className="rounded-full w-30 h-30 bg-black z-0 "/>
                       
                    </div> 
                    <div className="flex-1 h-full flex flex-col justify-center">
                         <h1 className="text-2xl font-bold">@username</h1>
                         <h1 className="text-lg font-thin text-[#999999]">u/username</h1>
                    </div>
                </div>
                <div className="w-full flex h-1/5 py-1 gap-3">
                    <button className="h-full rounded-full  text-sm  font-semibold  cursor-pointer px-6">Posts</button>
                    <button className="h-full rounded-full  text-sm font-semibold bg-[#c9d7de] cursor-pointer px-6">Comments</button>
                    <button className="h-full rounded-full  text-sm font-semibold  cursor-pointer px-6">Saved</button>
                </div>
            </div>
            <div className="flex-1  w-full">
                {posts.map((post) => (
                  <Comment 
                    key={post.id}
                    postOwner={post.owner}
                    commenter={post.owner}
                    time={new Date}
                    header={post.header}
                    votes={post.votes}
                    content={post.content}
                  />
                ))}
            </div>
      </div>
      <div className="w-1/5 h-full overflow-scroll bg-gray-200">
            
      </div>
    </div>
  </div>
}

export default Profile;