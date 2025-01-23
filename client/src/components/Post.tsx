import React from 'react';

interface Theme {
  text: string;
  background: string;
  primary: string;
  secondary: string;
  accent: string;
}

interface PostProps {
  theme: Theme;
  post: {
    id: number;
    author: string;
    avatar: string;
    content: string;
    timeAgo: string;
    images?: string[];
  };
}

const Post: React.FC<PostProps> = ({ theme, post }) => {
  return (
    <div 
      className="mb-4 rounded-lg p-4"
      style={{ backgroundColor: theme.secondary }}
    >
      <div className="flex items-center gap-3 mb-4">
        <img 
          src={post.avatar}
          alt={post.author} 
          className="w-10 h-10 rounded-full"
        />
        <div>
          <div style={{ color: theme.text }}>{post.author}</div>
          <div style={{ color: theme.primary }} className="text-sm">{post.timeAgo}</div>
        </div>
      </div>
      
      <p style={{ color: theme.text }} className="mb-4">{post.content}</p>
      
      {post.images && post.images.length > 0 && (
        <div className={`grid gap-2 mb-4 ${post.images.length === 1 ? '' : 'grid-cols-2'}`}>
          {post.images.map((image, index) => (
            <div 
              key={index} 
              className={`relative rounded-lg overflow-hidden ${
                post.images!.length === 1 ? 'max-h-[500px]' : 'aspect-square'
              }`}
            >
              <img
                src={image}
                alt={`Post image ${index + 1}`}
                className={`w-full h-full object-cover ${
                  post.images!.length === 1 ? 'object-contain' : 'object-cover'
                }`}
              />
            </div>
          ))}
        </div>
      )}

      <div className="flex gap-4 mt-4">
        <button
          className="flex items-center gap-2 px-3 py-2 rounded-lg transition-colors"
          style={{ color: theme.text }}
        >
          <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
            <path d="M2 10.5a1.5 1.5 0 113 0v6a1.5 1.5 0 01-3 0v-6zM6 10.333v5.43a2 2 0 001.106 1.79l.05.025A4 4 0 008.943 18h5.416a2 2 0 001.962-1.608l1.2-6A2 2 0 0015.56 8H12V4a2 2 0 00-2-2 1 1 0 00-1 1v.667a4 4 0 01-.8 2.4L6.8 7.933a4 4 0 00-.8 2.4z" />
          </svg>
          Like
        </button>
        <button
          className="flex items-center gap-2 px-3 py-2 rounded-lg transition-colors"
          style={{ color: theme.text }}
        >
          <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
            <path d="M15 8a3 3 0 10-2.977-2.63l-4.94 2.47a3 3 0 100 4.319l4.94 2.47a3 3 0 10.895-1.789l-4.94-2.47a3.027 3.027 0 000-.74l4.94-2.47C13.456 7.68 14.19 8 15 8z" />
          </svg>
          Share
        </button>
      </div>
    </div>
  );
};

export default Post; 