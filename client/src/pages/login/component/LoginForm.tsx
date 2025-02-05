import { useEffect, useState } from "react";
import AuthView from "./AuthViews";
import axios from "axios";
import User from "../../../Entities/User";




interface Props {
    switchView: (view: AuthView) => void;
  }

  const LoginForm: React.FC<Props> = ({ switchView }) => {
    return  <div className="max-w-full  z-1 bg-[#b4cea1] w-130  border border-[#bed1b0] drop-shadow-lg gap-5 px-15 py-8 max-h-full  flex flex-col rounded-xl">
    <h1 className="font-bold text-2xl">Login</h1>
    <p className="text-sm text-[#00000099]">
    By continuing, you agree to our User Agreement and acknowledge that you understand the Privacy Policy. 
    </p>
    <input placeholder="Email" className="outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2" />
    <input placeholder="Password" className=" outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"/>
    <h1 className="text-sm text-[#5a80e1] cursor-pointer" onClick={() => switchView(AuthView.FORGOT_PASSWORD)}>Forgot password?</h1>
    <h1 className="text-sm ">New to SpringOrbit? <span onClick={() => switchView(AuthView.SIGNUP)} className="text-[#5a80e1] cursor-pointer">Sign Up</span></h1>
    <button className="bg-[#77b5a3]  cursor-pointer rounded-3xl hover:bg-[#679c8e] text-xl text-white mt-15 py-2 px-2">Log In</button>


</div>
}



export default LoginForm;