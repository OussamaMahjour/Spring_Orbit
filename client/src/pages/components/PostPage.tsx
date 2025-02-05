import { Link, useParams } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";

import axios from "axios";
import { useState, useEffect } from "react";
import PostEntity from "../../Entities/Post";
import PrivateRoute from "./PrivateRoute";
import Header from "./Header";
import Post from "./Post";
import Sidebar from "./SideBar";
import PostForm from "./PostForm";
import CommentEntity from "../../Entities/Comment";
import Comment from "./Comment";
import Cookies from "js-cookie";
import User from "../../Entities/User";




const PostPage:React.FC = () => {
const {id} = useParams();
const [postsResponse, setPostsResponse] = useState<PostEntity>(
  {
    id:"0",
    user: {
      id: 0,
      name: "mahjour",
      gender: "male",
      email: "mahjour@gmail.com",
      verified: true,
      createdAt: new Date,
      deletedAt: new Date,
  },
    createAt: null,
    archived: false
}


);

  useEffect(() => {
     // Start loading bar when request begins
    axios.get("http://localhost:8084/POST-SERVICE/post/"+id)
      .then(response => {
        setPostsResponse(response.data);
       
      })
      .catch(error => {
        console.error("Error fetching posts:", error);
         
      });
  }, []);

//console.log("First post:", postsResponse.length > 0 ? postsResponse[0].id : "No posts available");

const [commentsResponse, setCommentsResponse] = useState<CommentEntity[]>([])
useEffect(() => {
  // Start loading bar when request begins
 axios.get("http://localhost:8084/COMMENT-SERVICE/comment/byPost/"+id)
   .then(response => {
     setCommentsResponse(response.data);
    
   })
   .catch(error => {
     console.error("Error fetching posts:", error);
      
   });
}, []);


const userCookie = Cookies.get("user");
const [userEntity,setUserEntity] = useState<User>()

useEffect(() => {
    // Start loading bar when request begins
   axios.get("http://localhost:8084/USER-SERVICE/api/v1/users/"+userCookie)
     .then(response => {
       setUserEntity(response.data);
      
     })
     .catch(error => {
       console.error("Error fetching posts:", error);
        
     });
 }, []);

const [userComment,setUserComment] = useState("");
const addComment = async () => {
    try {
      const response = await axios.post('http://localhost:8084/COMMENT-SERVICE/comment/add', {
        id: "32",
        userId: userEntity?.id,
        postId:postsResponse.id,
        content:userComment,
        createTime: ""
      });
      console.log(response.data);
      window.location.reload();
    } catch (error) {
      console.error('Error making POST request:', error);
    }
  };

return (
    <div className="w-screen h-screen max-h-screen flex flex-col">
    {PrivateRoute()}
    <Header />
    <div className="flex-1 min-h-0 flex w-full">
      <Sidebar />
      <div className="w-3/5 h-full px-5 flex flex-col gap-3 pt-3 overflow-scroll">
        
            <Post 
             postId={postsResponse.id}
             key={postsResponse.id} // Use a unique key
             comments={commentsResponse} // Modify if comments exist
             contentUrl="/image.png" // Modify based on API response
             header={postsResponse.user.name} // Assuming the user's name as the header
             postAvatarUrl="/image.png" // Modify based on API response
             postOwner={postsResponse.user.name}
             time={postsResponse.createAt ? new Date(postsResponse.createAt) : new Date()}
             votes={23}         
            />
            <div className="w-full border rounded-full flex overflow-hidden min-h-10 border-[#dddddd] ">
                <input onChange={(e)=>setUserComment(e.target.value)} placeholder="Add Comment" className=" px-8  h-10 outline-none w-full "/>
                <button onClick={addComment} className="cursor-pointer px-4 "><i className="fa-solid fa-paper-plane"></i></button>

            </div>
            {commentsResponse.length > 0 ? (
            commentsResponse.map((comment) => (
              <Comment 
              postOwner={postsResponse.user.name} 
              commenter={comment.user.name} 
              time={new Date} 
              header={""} 
              votes={0} 
              content={comment.content} />
            ))
          ) : (
            <p>Loading posts...</p>
          )}
      </div>
      <div className="w-1/5 h-full overflow-scroll bg-gray-200"></div>
    </div>
  </div>
);
}


export default PostPage;