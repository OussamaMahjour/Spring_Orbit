import User from "./User";
interface PostEntity {
    id: string,
    user: User,
    createAt: null,
    archived: false
}
export default PostEntity;