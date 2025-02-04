import AuthView from "./AuthViews";


interface Props {
    switchView: (view: AuthView) => void;
  }






const ForgotPasswordForm:React.FC<Props> = ({ switchView }) =>{
    return <div className="max-w-full  z-1 bg-[#b4cea1] w-130  border border-[#bed1b0] drop-shadow-lg gap-5 px-15 py-8 max-h-full  flex flex-col rounded-xl">
        <h1 className="font-bold text-2xl">Reset your password </h1>
        <p className="text-sm text-[#00000099]">
        Enter your email address  and we’ll send you a link to reset your password. 
        </p>
        <input placeholder="Email" className="outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2" />
        <button className="bg-[#77b5a3]  cursor-pointer rounded-3xl hover:bg-[#679c8e] text-xl text-white mt-15 py-2 px-2">Reset  password</button>

        </div>
}


export default ForgotPasswordForm;