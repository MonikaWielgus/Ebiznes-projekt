import React, {useEffect, useState} from 'react';
import Bar from "../components/Bar";
import Box from "@mui/material/Box";
import {Grid} from "@mui/material";
import {RemoteServer} from "../transport/RemoteServer";
import {NavLink, useParams} from "react-router-dom";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import Book from "../components/Book";

const CartPage = () => {

    const [products, setProducts] = useState([]);
    const { id } = useParams();

    const remoteServer = new RemoteServer();

    useEffect(() => {
        // zrobic prawdziwe id
        remoteServer
            .getProductsFromCart(1)
            .then(json => {
                setProducts(json.products)
            })
    }, []);

    return (
        <React.Fragment>
            <Bar/>
            <br/>
            <NavLink to={{
                pathname:`/`
            }}>
                <Button
                    style={{ float: 'right', marginRight: '16px'}}
                    variant="contained"
                    size="medium"
                >
                    Wyczyść koszyk
                </Button>
                <br/><br/>
            </NavLink>
            <br/>
            <Grid item sm={12}>
                <Box sx={{ width: '100%' }}>
                    <Grid container rowSpacing={1}>
                        {products.map((product) => (
                            <Book key={product.book.id} book = {product.book}/>
                        ))}
                    </Grid>
                </Box>
            </Grid>
        </React.Fragment>
    );
}

export default CartPage;