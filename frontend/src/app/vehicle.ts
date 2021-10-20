
import { Image } from "./image";

export interface Vehicle {
    id: number;
    make: string;
    model: string;
    year: number;
    consumption: number;
    photo: Image;
}