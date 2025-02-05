import { ArrowDown, ArrowUp, MessageCircle } from "lucide-react";
import React from "react";
import Media from "../../Entities/Media";

import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Comment from "../../Entities/Comment";
import CommentEntity from "../../Entities/Comment";

interface Props {
  postOwner: string;
  postAvatarUrl: string;
  time: Date;
  header: string;
  votes: number;
  contentUrl: string;
  comments: CommentEntity[];
  postId:string;
}

const Post: React.FC<Props> = ({
  postOwner,
  postAvatarUrl,
  time,
  header,
  votes,
  contentUrl,
  comments,
  postId,
}) => {
  const [mediaResponse, setMediaResponse] = useState<Media[]>([]);
  useEffect(() => {
    axios.get("http://localhost:8084/MEDIA-SERVICE/media/post/"+postId)
      .then(response => {
        setMediaResponse(response.data);
        //console.log("Fetched posts:", response.data); // Log response directly
      })
      .catch(error => {
        console.error("Error fetching posts:", error);

      });

  }, []);
  console.log(mediaResponse);
  const navigate = useNavigate();
  const [commentResponse, setCommentResponse] = useState<Comment[]>([]);
  useEffect(() => {
    axios.get("http://localhost:8084/COMMENT-SERVICE/media/post/"+postId)
      .then(response => {
        setCommentResponse(response.data);
      })
      .catch(error => {
        console.error("Error fetching posts:", error);

      });

  }, []);
  return (
    <div className="w-full cursor-pointer  flex border-b border-gray-300 p-4 bg-white rounded-lg shadow-sm" onClick={()=>{navigate("/post/"+postId)}}>
      {/* Voting Section */}
      <div className="flex flex-col items-center mr-4">
        <button className="text-gray-500 hover:text-red-500">
          <ArrowUp size={20} />
        </button>
        <span className="text-sm font-bold text-gray-700">{votes}</span>
        <button className="text-gray-500 hover:text-blue-500">
          <ArrowDown size={20} />
        </button>
      </div>

      {/* Post Content Section */}
      <div className="flex-1">
        <div className="flex items-center mb-2">
          <img
            src={postAvatarUrl}
            alt="Avatar"
            className="w-8 h-8 rounded-full mr-2"
          />
          <span className="text-sm font-semibold">{postOwner}</span>
          <span className="text-xs text-gray-500 ml-2">
            {new Date(time).toLocaleTimeString()}
          </span>
        </div>
        <h3 className="text-lg font-bold mb-2">{(mediaResponse.length > 0 ? mediaResponse[0].content:"")}</h3>
        {contentUrl && (
          <img
            src={"http://localhost:8084/MEDIA-SERVICE/media/"+(mediaResponse.length > 0 ? mediaResponse[0].id:"")}
            
            alt="Post content"
            className="w-full rounded-lg mb-2"
          />
        )}
        <div className="flex items-center text-gray-500 text-sm">
          <MessageCircle size={16} className="mr-1" />
          {comments.length} Comments
        </div>
      </div>
    </div>
  );
};

export default Post;
