import {Entity, Column, PrimaryColumn} from "typeorm";

@Entity()
export class Usuarios {

    @PrimaryColumn()
    usuario: string;

    @Column()
    nome: string;

    @Column()
    senha: string;

    @Column()
    pets: number;

}