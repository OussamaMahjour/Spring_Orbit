import { Navigate, Outlet } from "react-router-dom";
import Cookies from "js-cookie";

const PrivateRoute = () => {
  Cookies.set("user", "2", { expires: 7 }); // Save user for 7 days
  const user = Cookies.get("user"); // Get user from cookies

  return user ? <Outlet /> : <Navigate to="/login" replace />;
  
};

export default PrivateRoute;
