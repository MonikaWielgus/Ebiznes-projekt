import Bar from "../components/Bar";
import React from 'react';
import GoogleLogin from'../buttons/google-sign-in.png';


function LoginPage() {
    return (
        <React.Fragment>
            <Bar/>
            <a href='http://localhost:8080/api/auth' rel='noopener noreferrer'>
                <img src={GoogleLogin} alt={"google login button"}
                     style={{
                         margin: "auto",
                         marginTop: "200px",
                         display: "flex",
                         width: "20%"
                    }}
                />
            </a>

        </React.Fragment>
    );
}

export default LoginPage