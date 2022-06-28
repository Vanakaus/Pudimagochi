import {Entity, Column, PrimaryGeneratedColumn} from "typeorm";

@Entity()
export class User {

    @PrimaryGeneratedColumn()
    usuario: string;

    @Column()
    nome: string;

    @Column()
    senha: string;

}
