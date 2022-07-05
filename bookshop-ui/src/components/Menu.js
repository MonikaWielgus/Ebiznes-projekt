import React, {useEffect, useState} from "react";
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';
import {RemoteServer} from "../transport/RemoteServer";
import CategoryTab from "../tabs/CategoryTab";

const Menu = () => {
    const [value, setValue] = React.useState("1");
    const [categories, setCategories] = useState([]);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const remoteServer = new RemoteServer();

    useEffect(() => {
        remoteServer
            .getCategories()
            .then(json => {
                setCategories(json)
            })
    }, []);

    return (
        <Box sx={{ width: '100%', typography: 'body1' }}>
            <TabContext value={value}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <TabList onChange={handleChange}>
                        {categories.map((category) => (
                            <Tab
                                key={category.id}
                                label={category.name}
                                value={category.id.toString()}/>
                        ))}
                    </TabList>
                </Box>
                {categories.map((category) => (
                    <TabPanel
                        key={category.id}
                        value={category.id.toString()}
                    >
                        <CategoryTab
                            id={category.id}/>
                    </TabPanel>
                ))}
            </TabContext>
        </Box>
    );
}

export default Menu;
