import AuthView from "./AuthViews";


interface Props {
    switchView: (view: AuthView) => void;
  }


const SignUpForm: React.FC<Props> = ({ switchView })=> {
    return <div className="max-w-full  z-1 bg-[#b4cea1] w-130  border border-[#bed1b0] drop-shadow-lg gap-5 px-15 py-8 max-h-full flex flex-col rounded-xl">
<h1 className="font-bold text-2xl">Sign Up</h1>
    <p className="text-sm text-[#00000099]">
    By continuing, you agree to our User Agreement and acknowledge that you understand the Privacy Policy. 
    </p>
    <div className="w-full flex justify-between items-center gap-3">
    <input placeholder="First Name" className="outline-none bg-[#eef1ec] w-1/2 rounded-xl text-ml py-4 px-2" />
    <input placeholder="Last" className="outline-none bg-[#eef1ec] w-1/2  rounded-xl text-ml py-4 px-2" />
    </div>
    <input placeholder="Password" className=" outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"/>
    <input placeholder="Password" className=" outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"/>
    <input placeholder="Confirm Password" className=" outline-none bg-[#eef1ec] w-full rounded-xl text-ml py-4 px-2"/>
    <h1 className="text-sm ">Already in SpringOrbit? <span onClick={() => switchView(AuthView.LOGIN)} className="text-[#5a80e1] cursor-pointer">Log In</span></h1>

    <button className="bg-[#77b5a3]  cursor-pointer rounded-3xl hover:bg-[#679c8e] text-xl text-white mt-15 py-2 px-2">
        Sign Up
        </button>

    </div>
}


export default SignUpForm;