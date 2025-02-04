import React, { useState } from "react";
import axios from "axios";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const Login: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(""); // Clear previous error

    try {
      const response = await axios.post("http://localhost:8084/AUTHENTIFICATION-SERVICE/auth/login", {
        email,
        password,
      });
      
      // Store user ID in cookies
      Cookies.set("user", response.data.id, { expires: 1 }); // Expires in 1 day
      console.log("Login successful:", response.data);

      // Redirect to the home page
      navigate("/", { replace: true });
    } catch (error) {
      console.error("Login error:", error);
      setError("Invalid email or password. Please try again.");
    }
  };

  return (
    <div className="w-full relative min-h-screen flex flex-col justify-center items-center bg-gray-100">
      {/* Background */}
      <div className="log-background fixed w-screen h-screen bg-black z-0">
        <div className="bg-[#fafafa] w-full h-full"></div>
      </div>

      {/* Header */}
      <div className="max-w-full fixed bg-white shadow top-0 py-3 px-4 w-full max-h-14 items-center border-b border-black flex z-10 justify-start">
        <img src="./logo.png" alt="Logo" className="h-12" />
        <h1 className="text-2xl ml-3">
          <span className="text-[#586858]">Spring</span>
          <span className="text-[#8ead9e]">Orbit</span>
        </h1>
      </div>

      {/* Login Form */}
      <form
        onSubmit={handleSubmit}
        className="bg-white p-8 shadow rounded w-96 z-10 mt-16"
      >
        <h2 className="text-2xl font-bold mb-4">Login</h2>

        {error && (
          <p className="text-red-500 text-sm mb-4">{error}</p>
        )}

        <div className="mb-4">
          <label htmlFor="email" className="block text-sm font-medium mb-1">
            Email
          </label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full border rounded p-2"
            placeholder="Enter your email"
            required
          />
        </div>

        <div className="mb-6">
          <label htmlFor="password" className="block text-sm font-medium mb-1">
            Password
          </label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full border rounded p-2"
            placeholder="Enter your password"
            required
          />
        </div>

        <button
          type="submit"
          className="w-full bg-blue-500 text-white font-medium py-2 px-4 rounded hover:bg-blue-600"
        >
          Login
        </button>

        <p className="text-sm text-center mt-4">
          Don't have an account?{" "}
          <span
            className="text-blue-500 cursor-pointer"
            onClick={() => navigate("/signup")}
          >
            Sign up
          </span>
        </p>
      </form>
    </div>
  );
};

export default Login;
