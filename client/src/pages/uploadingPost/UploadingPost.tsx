import { Link } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";
import Header from "../components/Header";
import Sidebar from "../components/SideBar";
import Post from "../components/Post";
import axios from "axios";
import { useState, useEffect } from "react";
import PostEntity from "../../Entities/Post";
import PrivateRoute from "../components/PrivateRoute";
import PostForm from "../components/PostForm";


const UploadingPost = () => {

  return (
    <div className="w-screen h-screen max-h-screen flex flex-col">
      {PrivateRoute()}
      <Header />
      <div className="flex-1 min-h-0 flex w-full">
        <Sidebar />
        <div className="w-3/5 h-full overflow-scroll">
          <PostForm/>
        </div>
        <div className="w-1/5 h-full overflow-scroll bg-gray-200"></div>
      </div>
    </div>
  );
};

export default UploadingPost;
