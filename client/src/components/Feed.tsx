import React, { useState, useRef } from 'react';
import Post from './Post';

interface Theme {
  text: string;
  background: string;
  primary: string;
  secondary: string;
  accent: string;
}

interface FeedProps {
  theme: Theme;
}

const mockPosts = [
  {
    id: 1,
    author: 'Jeremy Meeks',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Jeremy',
    content: 'I was reading some interesting facts the other day and this stood out - "The first person convicted of speeding was going eight mph."',
    timeAgo: '12 days ago',
    images: ['https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80'],
  },
  {
    id: 2,
    author: 'Sarah Connor',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Sarah',
    content: 'Just finished my morning workout! 💪 Starting the day right!',
    timeAgo: '2 hours ago',
    images: [
      'https://images.unsplash.com/photo-1576678927484-cc907957088c?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80',
      'https://images.unsplash.com/photo-1549060279-7e168fcee0c2?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80'
    ],
  },
];

const Feed: React.FC<FeedProps> = ({ theme }) => {
  const [newPost, setNewPost] = useState('');
  const [selectedImages, setSelectedImages] = useState<File[]>([]);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleImageSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const filesArray = Array.from(e.target.files);
      setSelectedImages(prev => [...prev, ...filesArray].slice(0, 4)); // Limit to 4 images
    }
  };

  const removeImage = (index: number) => {
    setSelectedImages(prev => prev.filter((_, i) => i !== index));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Here you would typically upload images and create post
    console.log('New post:', { content: newPost, images: selectedImages });
    setNewPost('');
    setSelectedImages([]);
  };

  return (
    <div className="max-w-2xl mx-auto">
      <div 
        className="mb-8 rounded-lg p-4"
        style={{ backgroundColor: theme.secondary }}
      >
        <form onSubmit={handleSubmit}>
          <div className="flex gap-3">
            <img 
              src="https://api.dicebear.com/7.x/avataaars/svg?seed=Felix" 
              alt="Your avatar" 
              className="w-10 h-10 rounded-full"
            />
            <textarea
              placeholder="What's on your mind?"
              value={newPost}
              onChange={(e) => setNewPost(e.target.value)}
              className="flex-1 p-3 rounded-lg resize-none"
              style={{
                backgroundColor: theme.background,
                color: theme.text,
                borderColor: theme.primary,
              }}
              rows={3}
            />
          </div>

          {selectedImages.length > 0 && (
            <div className="grid grid-cols-2 gap-2 mt-4">
              {selectedImages.map((image, index) => (
                <div key={index} className="relative">
                  <img
                    src={URL.createObjectURL(image)}
                    alt={`Selected ${index + 1}`}
                    className="w-full h-32 object-cover rounded-lg"
                  />
                  <button
                    type="button"
                    onClick={() => removeImage(index)}
                    className="absolute top-1 right-1 p-1 rounded-full"
                    style={{ backgroundColor: theme.secondary }}
                  >
                    <svg className="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                      <path fillRule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clipRule="evenodd" />
                    </svg>
                  </button>
                </div>
              ))}
            </div>
          )}

          <div className="flex justify-between items-center mt-3">
            <button
              type="button"
              onClick={() => fileInputRef.current?.click()}
              className="flex items-center gap-2 px-3 py-2 rounded-lg transition-colors"
              style={{ color: theme.text }}
            >
              <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                <path fillRule="evenodd" d="M4 3a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V5a2 2 0 00-2-2H4zm12 12H4l4-8 3 6 2-4 3 6z" clipRule="evenodd" />
              </svg>
              Add Photos
            </button>
            <input
              type="file"
              ref={fileInputRef}
              onChange={handleImageSelect}
              multiple
              accept="image/*"
              className="hidden"
            />
            <button
              type="submit"
              className="px-4 py-2 rounded-lg transition-colors"
              style={{
                backgroundColor: theme.accent,
                color: theme.background,
              }}
            >
              Post
            </button>
          </div>
        </form>
      </div>

      {mockPosts.map(post => (
        <Post key={post.id} post={post} theme={theme} />
      ))}
    </div>
  );
};

export default Feed; 