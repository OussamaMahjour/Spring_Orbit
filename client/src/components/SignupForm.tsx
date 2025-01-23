import React, { useState } from 'react';

interface Theme {
  text: string;
  background: string;
  primary: string;
  secondary: string;
  accent: string;
}

interface SignupFormProps {
  theme: Theme;
}

const SignupForm: React.FC<SignupFormProps> = ({ theme }) => {
  const [formData, setFormData] = useState({
    name: '',
    gender: '',
    email: '',
    password: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log('Signup:', formData);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const inputStyle = {
    backgroundColor: theme.background,
    color: theme.text,
    borderColor: theme.primary,
  };

  const labelStyle = {
    color: theme.text
  };

  return (
    <form className="space-y-4" onSubmit={handleSubmit}>
      <div className="space-y-2">
        <label htmlFor="name" className="text-sm" style={labelStyle}>
          Name
        </label>
        <input
          id="name"
          name="name"
          type="text"
          value={formData.name}
          onChange={handleChange}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={inputStyle}
          required
        />
      </div>

      <div className="space-y-2">
        <label htmlFor="gender" className="text-sm" style={labelStyle}>
          Gender
        </label>
        <select
          id="gender"
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={inputStyle}
          required
        >
          <option value="" disabled>Select gender</option>
          <option value="male">Male</option>
          <option value="female">Female</option>
          
        </select>
      </div>

      <div className="space-y-2">
        <label htmlFor="email" className="text-sm" style={labelStyle}>
          Email
        </label>
        <input
          id="email"
          name="email"
          type="email"
          value={formData.email}
          onChange={handleChange}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={inputStyle}
          required
        />
      </div>

      <div className="space-y-2">
        <label htmlFor="password" className="text-sm" style={labelStyle}>
          Password
        </label>
        <input
          id="password"
          name="password"
          type="password"
          value={formData.password}
          onChange={handleChange}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={inputStyle}
          required
        />
      </div>

      <button
        type="submit"
        className="w-full py-2 rounded-md transition-colors mt-6"
        style={{ 
          backgroundColor: theme.accent,
          color: theme.background,
        }}
      >
        Sign up
      </button>
    </form>
  );
};

export default SignupForm; 