import { Link } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";



const Login = () =>{
    
const {start,complete} = useLoadingBar();

complete()
    return <div>
        From Login <br></br>
        <Link to="/" onClick={()=>start()}> 
           Go Home
        </Link>
    </div>
}

export default Login;