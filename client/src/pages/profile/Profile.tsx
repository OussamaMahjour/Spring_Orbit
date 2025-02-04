import { Link } from "react-router-dom";
import Header from "../components/Header";
import Sidebar from "../components/SideBar";
import Comment from "../components/Comment";

interface Props {
    
  }

const Profile:React.FC<Props> =  () => {
    return <div className="w-screen h-screen max-h-screen flex flex-col">
    <Header />
    {/* This div takes the remaining height after the header */}
    <div className="flex-1 min-h-0  flex w-full">
      {/* Sidebar - Takes the same height as the parent and allows scrolling */}
        <Sidebar />
      <div className="w-3/5 h-full px-10 py-6 flex flex-col overflow-scroll ">
            <div className="w-full flex flex-col h-60 min-h-60 border-b  border-[#00000022] ">
                <div className="w-full  flex h-4/5  items-center gap-5">
                    <div className="w-fit relative h-fit  ">
                        <Link to="/profile/edit" className="absolute bottom-0 right-0 w-10 h-10 rounded-full bg-[#dddddd] z-1 flex justify-center items-center">
                        <svg  aria-hidden="true" fill="currentColor" height="16" icon-name="add-media-outline" viewBox="0 0 20 20" width="16" xmlns="http://www.w3.org/2000/svg">
                                <path d="M17.376 3.032h-2.355L13.8 1.446A1.155 1.155 0 0 0 12.892 1h-5.74a1.17 1.17 0 0 0-.923.454L5.014 3.031H2.625A2.629 2.629 0 0 0 0 5.656v9.719A2.63 2.63 0 0 0 2.625 18h14.75A2.63 2.63 0 0 0 20 15.375V5.657a2.627 2.627 0 0 0-2.624-2.625Zm1.374 12.343a1.377 1.377 0 0 1-1.375 1.375H2.625a1.377 1.377 0 0 1-1.375-1.375V5.656a1.377 1.377 0 0 1 1.375-1.375h3L7.152 2.25l5.657-.041 1.6 2.072h2.971a1.375 1.375 0 0 1 1.37 1.376v9.718Zm-8.125-6H14v1.25h-3.375V14h-1.25v-3.375H6v-1.25h3.375V6h1.25v3.375Z"></path></svg>
                        </Link>
                            <img src="" className="rounded-full w-30 h-30 bg-black z-0 "/>
                       
                    </div> 
                    <div className="flex-1 h-full flex flex-col justify-center">
                         <h1 className="text-2xl font-bold">@username</h1>
                         <h1 className="text-lg font-thin text-[#999999]">u/username</h1>
                    </div>
                </div>
                <div className="w-full flex h-1/5 py-1 gap-3">
                    <button className="h-full rounded-full  text-sm  font-semibold  cursor-pointer px-6">Posts</button>
                    <button className="h-full rounded-full  text-sm font-semibold bg-[#c9d7de] cursor-pointer px-6">Comments</button>
                    <button className="h-full rounded-full  text-sm font-semibold  cursor-pointer px-6">Saved</button>
                </div>
            </div>
            <div className="flex-1  w-full">
                
                <Comment 
                postOwner={"oussama"} 
                commenter={"omar"} 
                time={new Date()} 
                header={"new car"} 
                votes={20}
                content="Nice Car"                
                />
                <Comment 
                postOwner={"oussama"} 
                commenter={"moktar"} 
                time={new Date()} 
                header={"Finishing Project"} 
                votes={30}
                content="Congratulation"                
                />
                <Comment 
                postOwner={"oussama"} 
                commenter={"oussama"} 
                time={new Date()} 
                header={"we did it "} 
                votes={120}
                content="yes we did"                
                />
                <Comment 
                postOwner={"oussama"} 
                commenter={"omar"} 
                time={new Date()} 
                header={"new car"} 
                votes={20}
                content="this the content"                
                />
                <Comment 
                postOwner={"oussama"} 
                commenter={"omar"} 
                time={new Date()} 
                header={"new car"} 
                votes={20}
                content="this the content"                
                />
                <button id="dropdownDefaultButton" data-dropdown-toggle="dropdown" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">Dropdown button <svg className="w-2.5 h-2.5 ms-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
<path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4"/>
</svg>
</button>


<div id="dropdown" className="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-44 dark:bg-gray-700">
    <ul className="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
      <li>
        <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard</a>
      </li>
      <li>
        <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Settings</a>
      </li>
      <li>
        <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Earnings</a>
      </li>
      <li>
        <a href="#" className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Sign out</a>
      </li>
    </ul>
</div>
            </div>
      </div>
      <div className="w-1/5 h-full overflow-scroll bg-gray-200">
            
      </div>
      
      
    </div>
  </div>
}

export default Profile;