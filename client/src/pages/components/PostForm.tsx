import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Cookies from "js-cookie";
import PrivateRoute from "./PrivateRoute";

const PostForm: React.FC = () => {
  const [formData, setFormData] = useState({
    user_id: Cookies.get("user"),
    type: "IMAGE",
    content: "",
    archived: "false",
  });
  const [file, setFile] = useState<File | null>(null);
  const navigate = useNavigate(); // Hook for navigation

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const data = new FormData();
    data.append("user_id", formData.user_id);
    data.append("type", formData.type);
    data.append("content", formData.content);
    data.append("archived", formData.archived);
    if (file) {
      data.append("media", file);
    }

    try {
      const response = await axios.post(
        "http://localhost:8084/POST-SERVICE/post/add",
        data,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      console.log("Response:", response.data);

      // Navigate to the home page after successful submission
      navigate("/", { replace: true });
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    
    <div className="p-4 max-w-md mx-auto">
        {PrivateRoute()}
      <h1 className="text-xl font-bold mb-4">Add Post</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-medium mb-1" htmlFor="user_id">
          </label>
          <input
            type="text"
            id="user_id"
            name="user_id"
            value={formData.user_id}
            onChange={handleChange}
            className="w-full border rounded p-2"
            hidden
          />
        </div>

        <div>
          <label className="block font-medium mb-1" htmlFor="type">
          </label>
          <input
            type="text"
            id="type"
            name="type"
            value={formData.type}
            onChange={handleChange}
            className="w-full border rounded p-2"
            hidden
          />
        </div>

        <div>
          <label className="block font-medium mb-1" htmlFor="content">
            Caption
          </label>
          <input
            type="text"
            id="content"
            name="content"
            value={formData.content}
            onChange={handleChange}
            className="w-full border rounded p-2"
            placeholder="Write a caption for your post"
          />
        </div>

        <div>
          <label className="block font-medium mb-1" htmlFor="media">
            Media
          </label>
          <input
            type="file"
            id="media"
            onChange={handleFileChange}
            className="w-full border rounded p-2"
          />
        </div>

        <div>
          <label className="block font-medium mb-1" htmlFor="archived">
          </label>
          <input
            type="text"
            id="archived"
            name="archived"
            value={formData.archived}
            onChange={handleChange}
            className="w-full border rounded p-2"
            hidden
          />
        </div>

        <button
          type="submit"
          className="bg-blue-500 text-white font-medium py-2 px-4 rounded hover:bg-blue-600"
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default PostForm;
