import {Entity, Column, PrimaryGeneratedColumn} from "typeorm";

@Entity()
export class Pets {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    nome: string;

    @Column()
    skin: string;

    @Column("decimal", { precision: 5, scale: 2 })
    saude: number;

    @Column("decimal", { precision: 5, scale: 2 })
    fome: number;

    @Column("decimal", { precision: 5, scale: 2 })
    energia: number;

    @Column("decimal", { precision: 5, scale: 2 })
    felicidade: number;

    @Column("decimal", { precision: 5, scale: 2 })
    higiene: number;

    @Column()
    estados: string;


    @Column()
    dono: string;
  
}