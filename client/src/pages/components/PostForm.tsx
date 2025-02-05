import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Cookies from "js-cookie";
import PrivateRoute from "./PrivateRoute";
import { HfInference } from "@huggingface/inference";


const client = new HfInference(import.meta.env.VITE_API);


console.log(import.meta.env.VITE_API)
const PostForm: React.FC = () => {
  const [formData, setFormData] = useState({
    user_id: Cookies.get("user"),
    type: "IMAGE",
    content: "",
    archived: "false",
  });
  const [file, setFile] = useState<File | null>(null);
  const [aiPopup, setAiPopup] = useState(false);
  const [aiPrompt, setAiPrompt] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

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
    //hello
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
      navigate("/", { replace: true });
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const generateCaption = async () => {
    if (!aiPrompt) return;
    setLoading(true);
    try {
      const chatCompletion = await client.chatCompletion({
        model: "meta-llama/Llama-2-7b-chat-hf",
        messages: [{
          role: "system",
          content: "Respond only with the generated text. Do not add greetings, explanations, or extra words, no quotation mark."
          },{ role: "user", content: aiPrompt }],
        provider: "together",
        max_tokens: 100,
      });
      setFormData((prev) => ({
        ...prev,
        content: chatCompletion.choices[0].message.content ?? "", // Default to an empty string if undefined
      }));
      
      
      setAiPopup(false);
    } catch (error) {
      console.error("AI Generation Error:", error);
    }
    setLoading(false);
  };
  
  return (
    <div className="p-4 max-w-md mx-auto">
      {PrivateRoute()}
      <h1 className="text-xl font-bold mb-4">Add Post</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-medium mb-1" htmlFor="content">Caption</label>
          <div className="flex items-center space-x-2">
            <input
              type="text"
              id="content"
              name="content"
              value={formData.content.replace("\"","")}
              onChange={handleChange}
              className="w-full border rounded p-2"
              placeholder="Write a caption for your post"
            />
            <button
              type="button"
              className="bg-gray-300 text-gray-700 px-3 py-2 rounded hover:bg-gray-400"
              onClick={() => setAiPopup(true)}
            >
              AI✨
            </button>
          </div>
        </div>

        <div>
          <label className="block font-medium mb-1" htmlFor="media">Media</label>
          <input
            type="file"
            id="media"
            onChange={handleFileChange}
            className="w-full border rounded p-2"
          />
        </div>

        <button
          type="submit"
          className="bg-blue-500 text-white font-medium py-2 px-4 rounded hover:bg-blue-600"
        >
          Submit
        </button>
      </form>

      {aiPopup && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-800/50">
          <div className="bg-white p-6 rounded shadow-lg w-96">
            <h2 className="text-lg font-bold mb-2">Generate Caption</h2>
            <input
              type="text"
              className="w-full border rounded p-2 mb-4"
              placeholder="Enter post topic..."
              value={aiPrompt}
              onChange={(e) => setAiPrompt(e.target.value)}
            />
            <div className="flex justify-end space-x-2">
              <button
                className="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400"
                onClick={() => setAiPopup(false)}
              >
                Cancel
              </button>
              <button
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                onClick={generateCaption}
                disabled={loading}
              >
                {loading ? "Generating..." : "Generate"}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PostForm;