import React from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import {Toolbar, Typography} from "@mui/material";
import {NavLink} from "react-router-dom";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCartOutlined';

const Bar = () => {
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
                    </Toolbar>
                </AppBar>
            </NavLink>
        </Box>
    );
}

export default Bar;
