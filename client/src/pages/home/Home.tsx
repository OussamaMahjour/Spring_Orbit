import { Link } from "react-router-dom";
import { useLoadingBar } from "react-top-loading-bar";



const Home = () => {
const {start,complete} = useLoadingBar();

complete()
    return <div>
        home
    </div>
}

export default Home;