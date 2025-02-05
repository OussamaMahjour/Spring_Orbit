import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { LoadingBarContainer } from 'react-top-loading-bar'
import Home from './pages/home/Home'
import Login from './pages/login/Login'
import Profile from './pages/profile/Profile'
import UploadingPost from './pages/uploadingPost/UploadingPost'
import PostForm from './pages/components/PostForm'
import PostPage from './pages/components/PostPage'





const router = createBrowserRouter([
  {
    path:"/",
    element:<Home/>
  },{
    path:"/login",
    element:<Login/>
  },  
  {
    path:"/profile",
    element:<Profile/>
  },
  {
    path:"/add",
    element:<UploadingPost/>
  },
  {
    path:"/post/:id",
    element:<PostPage />
  }

])
createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <LoadingBarContainer>
    <RouterProvider router={router}>

    </RouterProvider>
    </LoadingBarContainer>
  </StrictMode>,
)
