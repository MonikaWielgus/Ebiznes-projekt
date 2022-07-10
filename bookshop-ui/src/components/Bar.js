import React from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import {IconButton, Toolbar, Typography} from "@mui/material";
import {NavLink} from "react-router-dom";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCartOutlined';
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import { useCookies } from 'react-cookie';
import {RemoteServer} from "../transport/RemoteServer";

const Bar = () => {
    const [cookies] = useCookies(['user_session']);

    const remoteServer = new RemoteServer();

    return (
        <Box sx={{ flexGrow: 1 }}>
            <NavLink to='/' style={{ textDecoration: 'none' }}>
                <AppBar
                    position="static"
                >
                    <Toolbar>
                        <Typography variant="h5" component="div" sx={{ flexGrow: 1, mr: 2}}>
                            BOOKSHOP
                        </Typography>
                        <NavLink to='/cart' style={{color: "white", fontSize: "large"}}>
                            <ShoppingCartIcon/>
                        </NavLink>
                        {cookies.user_session !== undefined ?
                            <NavLink to='/logout' style={{color: "white", fontSize: "large", marginLeft: '10px'}}>
                                <LogoutIcon />
                            </NavLink>
                                :
                            <NavLink to='/login' style={{color: "white", fontSize: "large", marginLeft: '10px'}}>
                                <LoginIcon/>
                            </NavLink>
                        }
                    </Toolbar>
                </AppBar>
            </NavLink>
        </Box>
    );
}

export default Bar;
