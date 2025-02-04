interface User {
    id: number,
    name: string,
    gender: string,
    email: string,
    verified: boolean,
    createdAt: Date,
    deletedAt: Date,
}
export default User;