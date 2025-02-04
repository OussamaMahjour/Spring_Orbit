import Post from "./Post"

interface Media{
    id: string,
    post: Post,
    type: string,
    content: string,
    path: string
}

export default Media;