import React from "react";

type ButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement>;

export const Button: React.FC<ButtonProps> = ({ children, className, ...props }) => (
  <button
    className={`px-4 py-2 bg-emerald-500 text-white rounded hover:bg-emerald-600 ${className}`}
    {...props}
  >
    {children}
  </button>
);
