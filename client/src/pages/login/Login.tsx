import { createBrowserRouter, Link, Outlet, Route, Router, Routes } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";
import LoginForm from "./component/LoginForm";
import { useState } from "react";
import SignUpForm from "./component/SignupForm";
import AuthView from "./component/AuthViews";
import ForgotPasswordForm from "./component/ForgotPasswordForm";




const Login = () =>{
    
const {start,complete} = useLoadingBar();



  const [currentView, setCurrentView] = useState<AuthView>(AuthView.LOGIN);

complete()
    return <div className=" w-full relative   min-h-screen flex  flex-col justify-center items-center ">
            <div className="log-background fixed w-screen h-screen bg-black z-0" >
                <div className="bg-[#fafafa] w-full h-full ">

                </div>

            </div>
            <div className="max-w-full fixed bg-white shadow top-0 py-3 px-4  w-full  max-h-14  items-center border border-bottom border-black gap-3 flex z-10 justify-start"> 
                <img src="./logo.png" className="h-12"/>
                <h1 className="text-2xl">
                    <span className="text-[#586858]">Spring</span>
                    <span className="text-[#8ead9e]">Orbit</span>
                    </h1>
                </div>
                {currentView === AuthView.LOGIN && (
                    <LoginForm switchView={setCurrentView} />
                )}
                {currentView === AuthView.SIGNUP && (
                    <SignUpForm switchView={setCurrentView} />
                )}
                {currentView === AuthView.FORGOT_PASSWORD && (
                    <ForgotPasswordForm switchView={setCurrentView} />
                )}
           
    </div>
}

export default Login;