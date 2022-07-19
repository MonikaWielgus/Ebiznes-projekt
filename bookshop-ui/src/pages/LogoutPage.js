import Bar from "../components/Bar";
import React from 'react';
import LogoutIcon from "@mui/icons-material/Logout";
import {Grid, IconButton, Typography} from "@mui/material";
import Box from "@mui/material/Box";
import BookInCart from "../components/BookInCart";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

function LogoutPage() {
    return (
        <React.Fragment>
            <Bar/>
            <a href='http://localhost:8080/api/auth/logout' rel='noopener noreferrer' style={{color: "black", fontSize: "large", marginLeft: '10px', textDecoration: 'none'}}>
            <Box id='logout_button' sx={{ flexGrow: 1, marginTop: '200px', width: '80%', marginLeft: 'auto', marginRight: 'auto'}}>
                <Grid container spacing={2}>
                    <Grid item xs={6} md={12}>
                        <Item>
                            <Typography variant="h6">
                                Wyloguj
                            </Typography>
                        </Item>
                    </Grid>
                </Grid>
            </Box>
            </a>
        </React.Fragment>
    );
}

export default LogoutPage