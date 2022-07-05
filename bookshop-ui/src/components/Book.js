import React from "react";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import {Grid, Typography} from '@mui/material';
import Button from '@mui/material/Button';
import {NavLink} from "react-router-dom";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import InfoIcon from '@mui/icons-material/Info';
import {RemoteServer} from "../transport/RemoteServer";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'left',
    color: theme.palette.text.secondary,
}));

const Book = (props) => {
    const {
        book
    } = props;

    const remoteServer = new RemoteServer();

    function addToCart() {
        remoteServer
            .addProductToCart(1, book.id)
            .then(response => {
                if (response.status === 201) {
                    //TODO dodać alerty
                    console.log("super")
                } else {
                    console.log("coś poszło nie tak")
                }
            });
    }

    return (
        <React.Fragment>
            <Grid item sm={12}>
                <Item>
                    <Typography>{book.title}</Typography>
                        <Button
                            style={{ float: 'right', marginRight: '16px'}}
                            variant="contained"
                            size="small"
                            onClick={addToCart}
                        >
                            <AddShoppingCartIcon/>
                        </Button>
                    <NavLink to={{
                        pathname:`/book/${book.id}`
                    }}>
                        <Button
                            style={{ float: 'right', marginRight: '16px'}}
                            variant="contained"
                            size="small"
                        >
                            <InfoIcon/>
                        </Button>
                    </NavLink>
                </Item>
            </Grid>
        </React.Fragment>
    );
}

export default Book;