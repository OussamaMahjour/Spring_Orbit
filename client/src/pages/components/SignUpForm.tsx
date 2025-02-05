import React, { useState } from "react";
import axios from "axios";

const SignUpForm: React.FC = () => {
  const [formData, setFormData] = useState({
    name: "",
    gender: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Basic validation
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8084/AUTHENTIFICATION-SERVICE/auth/register", {
        name: formData.name,
        gender: formData.gender,
        email: formData.email,
        password: formData.password,
      });

      console.log("Registration successful:", response.data);
      alert("Registration successful!");
    } catch (error) {
      console.error("Error during registration:", error);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <div className="max-w-full z-1 bg-[#b4cea1] w-130 border border-[#bed1b0] drop-shadow-lg gap-5 px-15 py-8 max-h-full flex  items-center flex-col rounded-xl">
      <h1 className="font-bold text-2xl">Sign Up</h1>
      <p className="text-sm text-[#00000099]">
        By continuing, you agree to our User Agreement and acknowledge that you understand the Privacy Policy.
      </p>
      <div className="w-full flex justify-between items-center gap-3">
        <input
          name="name"
          value={formData.name}
          onChange={handleChange}
          placeholder="First Name"
          className="outline-none bg-[#eef1ec] w-1/2 rounded-xl text-ml py-4 px-2"
        />
        <input
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          placeholder="Gender"
          className="outline-none bg-[#eef1ec] w-1/2 rounded-xl text-ml py-4 px-2"
        />
      </div>
      <input
        name="email"
        value={formData.email}
        onChange={handleChange}
        placeholder="Email"
        className="outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"
      />
      <input
        name="password"
        type="password"
        value={formData.password}
        onChange={handleChange}
        placeholder="Password"
        className="outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"
      />
      <input
        name="confirmPassword"
        type="password"
        value={formData.confirmPassword}
        onChange={handleChange}
        placeholder="Confirm Password"
        className="outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"
      />
      <button
        onClick={handleSubmit}
        className="bg-[#77b5a3] cursor-pointer rounded-3xl hover:bg-[#679c8e] text-xl text-white mt-15 py-2 px-2"
      >
        Sign Up
      </button>
    </div>
  );
};

export default SignUpForm;