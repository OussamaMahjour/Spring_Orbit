import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { LoadingBarContainer } from 'react-top-loading-bar'
import Home from './pages/home/Home'
import Login from './pages/login/Login'





const router = createBrowserRouter([
  {
    path:"/",
    element:<Home/>
  },{
    path:"/login",
    element:<Login/>
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
