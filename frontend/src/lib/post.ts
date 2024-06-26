type Post = {
    id: number,
    spotifyUrl: string,
    postedAt: string,
    type: string,
    author: {
        id: string,
        email: string,
        country: string,
        imgUrl: string,
        displayName: string
    },
    description: string
}

async function getRecentPosts(page: number, size: number = 10) {
    const res = await fetch(`${import.meta.env.VITE_APP_BACKEND_URL}/api/v1/posts?page=${page}&size=${size}`, {
        method: 'GET'
    })
    const json = await res.json()
    return json
}

async function getRecommendedPosts(id: string): Promise<[Post]> {
    const res = await fetch(`${import.meta.env.VITE_APP_BACKEND_URL}/api/v1/posts/recommended/${id}`, {
        method: 'GET'
    })
    const json = await res.json()
    return json
}

async function getUserPosts(id?: string) {
    const res = await fetch(`${import.meta.env.VITE_APP_BACKEND_URL}/api/v1/posts/${id}`, {
        method: 'GET'
    })
    const json = await res.json()
    return json
}

async function createPost(authorId: string, type: string, description: string, spotifyUrl: string) {
    const res = await fetch(`${import.meta.env.VITE_APP_BACKEND_URL}/api/v1/posts`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('access_token')}`
        },
        body: JSON.stringify({
            authorId: authorId,
            type: type,
            description: description,
            spotifyUrl: spotifyUrl
        })
    })
    const json = await res.json()
    return json
}

export { getRecentPosts, getRecommendedPosts, getUserPosts, createPost }