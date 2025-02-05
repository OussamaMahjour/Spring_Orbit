import Post from "./Post"
import User from "./User";

interface CommentEntity{
    id: string,
    post: Post,
    user:User
    content: string,
    creatTime: Date
}

export default CommentEntity;