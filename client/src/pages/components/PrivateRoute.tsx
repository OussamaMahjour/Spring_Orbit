import { Navigate, Outlet } from "react-router-dom";
import Cookies from "js-cookie";

const PrivateRoute = () => {
  const user = Cookies.get("user"); // Get user from cookies

  return user ? <Outlet /> : <Navigate to="/login" replace />;
  
};

export default PrivateRoute;
