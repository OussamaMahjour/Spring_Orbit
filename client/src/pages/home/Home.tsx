import { Link } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";
import Header from "../components/Header";
import Sidebar from "../components/SideBar";
import Post from "../components/Post";
import axios from "axios";
import { useState, useEffect } from "react";
import PostEntity from "../../Entities/Post";
import PrivateRoute from "../components/PrivateRoute";


const Home = () => {
  const { start, complete } = useLoadingBar();
  const [postsResponse, setPostsResponse] = useState<PostEntity[]>([]);

  useEffect(() => {
    start(); // Start loading bar when request begins
    axios.get("http://localhost:8084/POST-SERVICE/post/all")
      .then(response => {
        setPostsResponse(response.data);
        //console.log("Fetched posts:", response.data); // Log response directly
        complete(); 
      })
      .catch(error => {
        //console.error("Error fetching posts:", error);
        complete(); 
      });
  }, []);
  
  //console.log("First post:", postsResponse.length > 0 ? postsResponse[0].id : "No posts available");
  

  return (
    <div className="w-screen h-screen max-h-screen flex flex-col">
      {PrivateRoute()}
      <Header />
      <div className="flex-1 min-h-0 flex w-full">
        <Sidebar />
        <div className="w-3/5 h-full px-19 flex flex-col gap-3 pt-3 overflow-scroll">
          {postsResponse.length > 0 ? (
            postsResponse.map((post) => (
              <Post
                postId={post.id}
                key={post.id} // Use a unique key
                comments={[]} // Modify if comments exist
                contentUrl="./image.png" // Modify based on API response
                header={post.user.name} // Assuming the user's name as the header
                postAvatarUrl="./image.png" // Modify based on API response
                postOwner={post.user.name}
                time={post.createAt ? new Date(post.createAt) : new Date()}
                votes={23} // Modify based on API response
              />
            ))
          ) : (
            <p>Loading posts...</p>
          )}
        </div>
        <div className="w-1/5 h-full overflow-scroll bg-gray-200"></div>
      </div>
    </div>
  );
};

export default Home;
