import "reflect-metadata";
import * as express from "express";
import {Request, Response} from "express";
import {createConnection} from "typeorm";
import {Usuarios} from "./entity/Usuarios";
import {Pets} from "./entity/Pets";

 
createConnection({
  type: "mysql",
  host: "localhost",
  port: 3306,
  username: "root",
  password: "root",
  database: "PudiServer",
  entities: [
      __dirname + "/entity/*.ts"
  ],
  synchronize: true,
  logging: false
}).then(async connection => {
  console.log('Server Ligado');
  console.log('Escutando na porta 3000'+'\n');
  console.log('\n'+'Rotas:'+'\n');
  console.log('/users (post)');
  console.log('/users/:user (get)');
  console.log('/pet (post)');
  console.log('/pets/:dono (get)');
  console.log('/pet/:id (get)'+'\n');


  const userRepository = connection.getRepository(Usuarios);
  const petRepository = connection.getRepository(Pets);


  const server = express();
  server.use(express.json());



  server.post("/users", async function(req: Request, res: Response) {
    
    console.log('Adicionando uauário'+'\n');

    const user = await userRepository.create(req.body);
    const results = await userRepository.save(user);

    return res.send(results);
  });



  server.get("/users/:user", async function(req: Request, res: Response) {

    console.log('Procurando usuário'+'\n');

    const users = await userRepository.findOne(req.params.user);

    res.json(users);
  });


  server.post("/updateUser", async function(req: Request, res: Response) {
    
    console.log('Atualizando Usuario'+'\n');

    
    const update = await userRepository.create(req.body);

    var val = Object.keys(update).map(function(personNamedIndex){
      let valores = update[personNamedIndex];
      // do something with person
      return valores;
    });

    const user = await userRepository.findOne(val[0]);

    user.pets = val[1];
    
    const results = await userRepository.save(user);
    
    res.json(results);
  });







  server.post("/pet", async function(req: Request, res: Response) {
    
    console.log('Adicionado Pet'+'\n');

    const pet = await petRepository.create(req.body);
    const results = await petRepository.save(pet);

    return res.send(results);
  });



  server.get("/pets/:dono", async function(req: Request, res: Response) {

    const pets = await petRepository.find({ dono: req.params.dono});

    console.log('Procurando Pets'+'\n');

    res.json(pets);
  });



  server.get("/pet/:id", async function(req: Request, res: Response) {
    
    const Pet = await petRepository.findOne(req.params.id);

    console.log('Procurando Pet'+'\n');
    
    res.json(Pet);
  });



  server.post("/update", async function(req: Request, res: Response) {
    
    console.log('Atualizando Pet'+'\n');

    
    const update = await petRepository.create(req.body);

    var val = Object.keys(update).map(function(personNamedIndex){
      let valores = update[personNamedIndex];
      // do something with person
      return valores;
    });

    const pet = await petRepository.findOne(val[0]);

    pet.saude = val[1];
    pet.fome = val[2];
    pet.energia = val[3];
    pet.felicidade = val[4];
    pet.higiene = val[5];
    pet.estados = val[6];
    
    const results = await petRepository.save(pet);
    
    res.json(results);
  });
    
  
  server.listen(3000);
 
}).catch(error => console.log(error));