import React, { Component } from "react";
import Avatar from "./recursos/imagenes/avatar.png";
import Formulario from './Componentes/Formulario';
import Usuario from './Componentes/Usuario'
import { BrowserRouter, NavLink, Route, Routes } from 'react-router-dom'
import './recursos/CSS/menu.css';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usuarios: []
        }
    }

    componentDidMount() {
        this.buscarUsuarios();
    }

    buscarUsuarios = () => {
        const URL = "https://reqres.in/api/users";
        fetch(URL).
            then((response) => response.json()).
            then((json) => this.setState({ usuarios: json.data })).
            catch((error) => console.log(error));
    }

    agregarUsuarioArreglo = (correo, nombre, apellido) => {
        const usuario = {
            email: correo,
            first_name: nombre,
            last_name: apellido,
            avatar: Avatar
        }
        this.agregarUsuarioAPI(usuario);
    }

    agregarUsuarioAPI = (usuario) => {
        const URL = "https://reqres.in/api/users";
        const HEADER = {
            method: "POST",
            body: JSON.stringify(usuario),
            headers: {
                "Content-Type": "application/json"
            }
        }
        fetch(URL, HEADER).
            then((response) => response.json()).
            then((json) => this.setState({ usuarios: [...this.state.usuarios, json] })).
            catch((error) => console.log(error));
    }



    render() {
        return (<>

            <BrowserRouter>

                <nav className="menu">
                    <NavLink className="enlace" activeClassName="activo" to="/" >Inicio</NavLink>

                    <NavLink className="enlace" activeClassName="activo" to="/formulario" >Formulario</NavLink>

                    <NavLink className="enlace" activeClassName="activo" to="/usuarios">Usuarios</NavLink>
                </nav>



                <Routes>
                    <Route index element={<h1>Bienvenidos</h1>} />
                    <Route path="/formulario" element={<Formulario agregarUsuario={this.agregarUsuarioArreglo} />} />
                    <Route path="/usuarios" element={this.state.usuarios.map((e) =>
                        <Usuario
                            key={e.id}
                            id={e.id}
                            email={e.email}
                            first_name={e.first_name}
                            last_name={e.last_name}
                            avatar={e.avatar}
                        />
                    )} />

                </Routes>
            </BrowserRouter>









        </>);
    }
}

export default App;

