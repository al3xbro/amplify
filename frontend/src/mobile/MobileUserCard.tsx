import { Link } from "react-router-dom"

type User = {
    id: string,
    displayName: string,
    imgUrl: string,
    country: string
}

type Props = {
    user: User,
}

export default function MobileUserCard({ user }: Props) {
    return (
        <Link className="flex flex-col gap-2 w-[244px] p-6 rounded-xl duration-200 shadow-xl transition ease-in-out bg-space-light hover:bg-space-lighter" to={`/profile/${user.id}`}>
            <img className="object-cover w-full aspect-square rounded-full shadow-lg" src={user.imgUrl}></img>
            <p className="text-lg">{user.displayName}</p>
            <p className="text-space-lightest">Profile</p>
        </Link>
    )
}
