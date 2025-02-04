import { Link } from "react-router-dom";

const Header:React.FC = () => {
    return <div className="w-full h-14  flex px-5 justify-around border-b border-[#00000022]">
        <Link to="/"  className="w-1/5 flex justify-start items-center cursor-pointer">
            
            <img src="./logo.png" className="h-11  cursor-pointer"/>
                <h1 className="text-xl ">
                    <span className="text-[#586858]">Spring</span>
                    <span className="text-[#8ead9e]">Orbit</span>
            </h1>
            </Link>
        
        <div className="w-3/5 flex justify-center items-center">
                <div className="w-3/4 h-9 rounded-3xl bg-[#e5ebee] px-5 flex items-center">
                    <i className="fa-solid font-light text-[#00000099] text-xl  fa-magnifying-glass"></i>
                    <input  className="flex-1 outline-none text-sm px-3" placeholder="Search..."/>
                </div>

        </div>
        <div className="w-1/5 text-2xl justify-end pr-3 gap-5 flex items-center">
            <i className="fa-regular fa-bell cursor-pointer"></i>
            <div className="flex text-lg justify-center items-center gap-1 cursor-pointer">
            <svg fill="currentColor" height="20" icon-name="add-outline" viewBox="0 0 20 20" width="20" xmlns="http://www.w3.org/2000/svg">
      <path d="M19 9.375h-8.375V1h-1.25v8.375H1v1.25h8.375V19h1.25v-8.375H19v-1.25Z"></path>
    </svg>
                <h1>Create</h1>
            </div>
            <i className="fa-solid fa-inbox cursor-pointer"></i>
            <Link  to="/profile"><img className="rounded-full overflow-hidden h-7 w-7 bg-black cursor-pointer"/></Link>
            
        </div>

    </div>
}



export default Header;