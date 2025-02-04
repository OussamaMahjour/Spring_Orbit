

interface Props {
    postOwner:string,
    commenter:string,
    time:Date,
    header:string,
    votes:number,
    content:string


}


const Comment:React.FC<Props> = (props:Props) =>{
    return <div className="w-full   flex   border-b m-3 py-3  border-[#00000022]">
        <div className="w-7  mr-3">
            <img className="h-7 w-7 bg-black rounded-full " />
        </div>
        <div className="flex-1 flex-col flex gap-2">
            <div className="w-full  flex items-center gap-2 h-1/4 ">
                
                <h1 className="text-sm font-bold">u/{props.postOwner}</h1>
                <h1 className="font-bold">{">"}</h1>
                <h1 className="text-xs ">{props.header}</h1>
                <h1 className="right-0">...</h1>
            </div>
            <div className="w-full h-1/4 ">
                <h1 className="text-sm text-[#aaaaaa]"><span className="text-black font-bold">{props.commenter}</span> commented on {props.time.toDateString()}</h1>
            </div>
            <div className="w-full h-1/4 ">
                <h1 className="font-sm">{props.content}</h1>
            </div>
            <div className="w-full text-sm h-1/4 flex gap-2 items-center">
                <svg  fill="currentColor" height="16" icon-name="upvote-fill" viewBox="0 0 20 20" width="16" xmlns="http://www.w3.org/2000/svg"> <path d="M10 19c-.072 0-.145 0-.218-.006A4.1 4.1 0 0 1 6 14.816V11H2.862a1.751 1.751 0 0 1-1.234-2.993L9.41.28a.836.836 0 0 1 1.18 0l7.782 7.727A1.751 1.751 0 0 1 17.139 11H14v3.882a4.134 4.134 0 0 1-.854 2.592A3.99 3.99 0 0 1 10 19Z"></path> </svg>
                <h1>{props.votes}</h1>
                <svg  fill="currentColor" height="16" icon-name="downvote-outline" viewBox="0 0 20 20" width="16" xmlns="http://www.w3.org/2000/svg"> <path d="M10 1c.072 0 .145 0 .218.006A4.1 4.1 0 0 1 14 5.184V9h3.138a1.751 1.751 0 0 1 1.234 2.993L10.59 19.72a.836.836 0 0 1-1.18 0l-7.782-7.727A1.751 1.751 0 0 1 2.861 9H6V5.118a4.134 4.134 0 0 1 .854-2.592A3.99 3.99 0 0 1 10 1Zm0 17.193 7.315-7.264a.251.251 0 0 0-.177-.429H12.5V5.184A2.631 2.631 0 0 0 10.136 2.5a2.441 2.441 0 0 0-1.856.682A2.478 2.478 0 0 0 7.5 5v5.5H2.861a.251.251 0 0 0-.176.429L10 18.193Z"></path> </svg>
            </div>
        </div>
        

    </div>
}


export default Comment;