import React, { useState } from "react";
import axios from "axios";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import SignUpForm from "../components/SignUpForm";

const Register: React.FC = () => {

  return (
    <div className="w-full h-full flex justify-center items-center ">
   <SignUpForm/>
   </div>

  );
};

export default Register;