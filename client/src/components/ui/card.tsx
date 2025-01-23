import React from "react";

export const Card: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <div className="bg-white shadow-md rounded-lg">{children}</div>
);

export const CardHeader: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <div className="p-4 border-b border-gray-200">{children}</div>
);

export const CardContent: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <div className="p-4">{children}</div>
);

export const CardTitle: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <h2 className="text-lg font-semibold">{children}</h2>
);
