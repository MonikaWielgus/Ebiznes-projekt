import React, {useEffect, useState} from 'react';
import Bar from "../components/Bar";
import {Divider, Grid, Typography} from "@mui/material";
import {RemoteServer} from "../transport/RemoteServer";
import {useParams} from "react-router-dom";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart'

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

    const remoteServer = new RemoteServer();

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
            <Grid item sm={12}>
                <Item>
                    <Typography variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Tytuł
                    </Typography>
                    <Divider/>
                    <Typography sx={{ color: 'text.secondary' }}>{book.title}</Typography>
                </Item>
                <Item>
                    <Typography variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Autor
                    </Typography>
                    <Divider/>
                    <Typography sx={{ color: 'text.secondary' }}>{book.author}</Typography>
                </Item>
                <Item>
                    <Typography variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Kategoria
                    </Typography>
                    <Divider/>
                    <Typography sx={{ color: 'text.secondary' }}>{book.category}</Typography>
                </Item>
                <Item>
                    <Typography variant="h6" sx={{ width: '33%', flexShrink: 0 }}>
                        Cena
                    </Typography>
                    <Divider/>
                    <Typography sx={{ color: 'text.secondary' }}>{book.price}</Typography>
                </Item>
                    <Button
                        style={{ float: 'right', marginRight: '16px'}}
                        variant="contained"
                        size="small"
                    >
                        <AddShoppingCartIcon/>
                    </Button>
            </Grid>
        </React.Fragment>
    );
}

export default BookPage;