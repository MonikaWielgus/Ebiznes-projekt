import Bar from "../components/Bar";
import React from 'react';
import LogoutIcon from "@mui/icons-material/Logout";
import {IconButton, Typography} from "@mui/material";


function LogoutPage() {
    return (
        <React.Fragment>
            <Bar/>
            <a href='http://localhost:8080/api/auth/logout' rel='noopener noreferrer' style={{color: "black", fontSize: "large", marginLeft: '10px'}}>
                <Typography
                    variant="h6" sx={{ width: '33%', flexShrink: 0 }}
                    style={{
                        margin: "auto",
                        marginTop: "200px",
                        display: "flex",
                        width: "20%"
                    }}>
                    Wyloguj
                </Typography>
            </a>

        </React.Fragment>
    );
}

export default LogoutPage