import React from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import {Toolbar, Typography} from "@mui/material";
import {NavLink} from "react-router-dom";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCartOutlined';
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import { useCookies } from 'react-cookie';

const Bar = () => {
    const [cookies] = useCookies(['user_session']);


    return (
        <Box sx={{ flexGrow: 1 }} id={'bar'}>
            <NavLink to='/' id='back_to_main_page' style={{ textDecoration: 'none' }}>
                <AppBar
                    position="static"
                >
                    <Toolbar>
                        <Typography id={'title'} variant="h5" component="div" sx={{ flexGrow: 1, mr: 2}}>
                            BOOKSHOP
                        </Typography>
                        <NavLink to='/cart' id='cart_icon' style={{color: "white", fontSize: "large"}}>
                            <ShoppingCartIcon/>
                        </NavLink>
                        {cookies.user_session !== undefined ?
                            <NavLink to='/logout' id='logout_icon' style={{color: "white", fontSize: "large", marginLeft: '10px'}}>
                                <LogoutIcon />
                            </NavLink>
                                :
                            <NavLink to='/login' id='login_icon' style={{color: "white", fontSize: "large", marginLeft: '10px'}}>
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
