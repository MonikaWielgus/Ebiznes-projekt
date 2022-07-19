import React, {useEffect, useState} from 'react';
import {RemoteServer} from "../transport/RemoteServer";
import {Grid} from '@mui/material';
import Box from '@mui/material/Box';
import Book from "../components/Book";


const CategoryTab = (props) => {
    const {
        id
    } = props;

    const [books, setBooks] = useState([]);

    const remoteServer = new RemoteServer();

    useEffect(() => {
        remoteServer
            .getBooksByCategoryId(id)
            .then(json => {
                setBooks(json)
            })
    }, []);

    return (
        <Box sx={{ width: '100%' }} id={"tab_" + props.id}>
            <Grid container rowSpacing={1}>
                {books.map((book) => (
                    <Book key={book.id} book = {book}/>
                ))}
            </Grid>
        </Box>
    );
}

export default CategoryTab;