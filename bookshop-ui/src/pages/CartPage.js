import React, {useEffect, useState} from 'react';
import Bar from "../components/Bar";
import Box from "@mui/material/Box";
import {Grid, Typography} from "@mui/material";
import {RemoteServer} from "../transport/RemoteServer";
import {useNavigate, useParams} from "react-router-dom";
import Button from "@mui/material/Button";
import BookInCart from "../components/BookInCart";

const CartPage = () => {

    const [products, setProducts] = useState([]);

    const navigate = useNavigate();
    const remoteServer = new RemoteServer();

    const removeAll = () => {
        remoteServer
            .removeAllFromProductsCart()
            .then(response => {
                if (response.status === 201) {
                    window.location.reload()
                }
            })
    };

    useEffect(() => {
        remoteServer
            .getProductsFromCart()
            .then(async response => {
                if (response.status === 403) {
                    navigate('/login')
                } else {
                    let result = JSON.parse(await response.text())
                    setProducts(result.products)
                }
            })
    }, []);
    return (
        <React.Fragment>
            <Bar/>
            <br/>
                <Button
                    id={'clean_cart'}
                    style={{ float: 'right', marginRight: '16px'}}
                    variant="contained"
                    size="medium"
                    onClick={removeAll}
                >
                    Wyczyść koszyk
                </Button>
                <br/><br/>
            <br/>
            {products.length !== 0 ?
            <Grid item sm={12}>
                <Box sx={{ width: '100%' }}>
                    <Grid container rowSpacing={1}>
                        {products.map((product) => (
                            <BookInCart key={product.book.id} book = {product.book} amount = {product.amount}/>
                        ))}
                    </Grid>
                </Box>
            </Grid> :
            <Typography id={'empty_cart'} variant="h5" component="div" style={{textAlign: 'center'}}>
                Koszyk jest pusty
            </Typography> }
        </React.Fragment>
    );
}

export default CartPage;