import React, {useEffect, useState} from 'react';
import Bar from "../components/Bar";
import {Collapse, Divider, Grid, IconButton, Typography} from "@mui/material";
import {RemoteServer} from "../transport/RemoteServer";
import {useNavigate, useParams} from "react-router-dom";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart'
import {Alert} from "@mui/lab";
import CloseIcon from '@mui/icons-material/Close';
import Box from "@mui/material/Box";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'left',
    color: theme.palette.text.secondary,
}));

const BookPage = () => {

    const [book, setBook] = useState([]);
    const { id } = useParams();

    const navigate = useNavigate();
    const remoteServer = new RemoteServer();

    const [infoOpen, setInfoOpen] = React.useState(false);

    const sleep = ms => new Promise(
        resolve => setTimeout(resolve, ms)
    );

    function addToCart() {
        remoteServer
            .addProductToCart(book.id)
            .then(async response => {
                if (response.status === 201) {
                    setInfoOpen(true);
                    await sleep(1000);
                    setInfoOpen(false);
                }
                if (response.status === 403) {
                    navigate('/login')
                }
            });
    }

    useEffect(() => {
        remoteServer
            .getBookWithCategory(id)
            .then(json => {
                setBook(json)
            })
    }, []);

    return (
        <React.Fragment>
            <Bar/>
            <br/>
            <Box sx={{ width: '100%' }}>
                <Collapse in={infoOpen}>
                    <Alert
                        id={'book_added'}
                        action={
                            <IconButton
                                aria-label="close"
                                color="inherit"
                                size="small"
                                onClick={() => {
                                    setInfoOpen(false);
                                }}
                            >
                                <CloseIcon fontSize="inherit" />
                            </IconButton>
                        }
                        sx={{ mb: 2 }}
                    >
                        Książka dodana poprawnie!
                    </Alert>
                </Collapse>
            </Box>
            <Grid item sm={12}>
                <Item>
                    <Typography id={'title_header_' + book.id} variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Tytuł
                    </Typography>
                    <Divider/>
                    <Typography id={'book_title_' + book.id} sx={{ color: 'text.secondary' }}>{book.title}</Typography>
                </Item>
                <Item>
                    <Typography id={'author_header_' + book.id} variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Autor
                    </Typography>
                    <Divider/>
                    <Typography id={'book_author_' + book.id} sx={{ color: 'text.secondary' }}>{book.author}</Typography>
                </Item>
                <Item>
                    <Typography id={'category_header_' + book.id} variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Kategoria
                    </Typography>
                    <Divider/>
                    <Typography id={'book_category_' + book.id} sx={{ color: 'text.secondary' }}>{book.category}</Typography>
                </Item>
                <Item>
                    <Typography id={'price_header_' + book.id} variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Cena
                    </Typography>
                    <Divider/>
                    <Typography id={'book_price_' + book.id} sx={{ color: 'text.secondary' }}>{book.price}</Typography>
                </Item>
                    <Button
                        id={'book_page_add_to_cart_' + book.id}
                        style={{ float: 'right', marginRight: '16px'}}
                        variant="contained"
                        size="small"
                        onClick={addToCart}
                    >
                        <AddShoppingCartIcon/>
                    </Button>
            </Grid>
        </React.Fragment>
    );
}

export default BookPage;