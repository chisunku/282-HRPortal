import React, { useState, useEffect } from 'react';
import { Table, TableHead, TableRow, TableCell, TableBody, TableSortLabel } from '@material-ui/core';
import { service } from '../services.js';
import ReactPaginate from "react-paginate";

const ITEMS_PER_PAGE = 10;

function table (props){    

    const[data, setData] = useState(props.data);

    console.log("in table: ",data);

    const [currentPage, setCurrentPage] = useState(0);

    const handlePageClick = ({ selected }) => {
      setCurrentPage(selected);
    };

    // const startIndex = currentPage * ITEMS_PER_PAGE;
    // const endIndex = startIndex + ITEMS_PER_PAGE;
    // const currentItems = data.slice(startIndex, endIndex);

    if (data === undefined) {
      return <p>Loading...</p>;
    }

    useEffect(() => {
      setData(props.data);
    }, [props.data]);

    const empID = localStorage.getItem('employee');

    // useEffect( () => {
    //   async function getEmpDetails(){
    //     await service.teamInfo(empID).then(resp => resp.json()).then(data => {
    //       setData(data.employees);
    //       console.log("in about: ",typeof(data)," ",data," keys:",Object.keys(data));
    //     });
    //   }
    // getEmpDetails();
    // },[]);


    // const objStr = `Emp No: ${data.empNo}, Name: ${data.empFirstName} ${data.empLastName}, Title: ${data.empTitle}`;

    if (data === undefined || data.length===0) {
      return <p>Loading...</p>;
    }

    const columns = [
      { id: 'empNo', label: 'Employee No' },
      { id: 'empFirstName', label: 'Employee First Name' },
      { id: 'empLastName', label: 'Employee Last Name' },
    ];
      // const [orderBy, setOrderBy] = useState('');
      // const [order, setOrder] = useState('asc');
    
      // const handleSort = (columnId) => {
      //   if (orderBy === columnId) {
      //     setOrder(order === 'asc' ? 'desc' : 'asc');
      //   } else {
      //     setOrderBy(columnId);
      //     setOrder('asc');
      //   }
      // };

      // const dataDuplicate = data;
    
      // const sortedData = dataDuplicate.sort((a, b) => {
      //   const column = columns.find(c => c.id === orderBy);
      //   if (column) {
      //     if (order === 'asc') {
      //       return a[column.id] > b[column.id] ? 1 : -1;
      //     } else {
      //       return a[column.id] < b[column.id] ? 1 : -1;
      //     }
      //   } else {
      //     return 0;
      //   }
      // });

  return (
    <div>
      {/* {data.map((emp, index) => (
        <p key={index}>[{emp.empNo}] {emp.empFirstName} {emp.empLastName}, {emp.empTitle}</p>
      ))} */}

<Table>
      <TableHead>
        {/* <TableRow>
          {columns.map((column) => (
            <TableCell key={column.id}>
              <TableSortLabel
                // active={orderBy === column.id}
                // direction={orderBy === column.id ? order : 'asc'}
                // onClick={() => handleSort(column.id)}
              >
                {column.label}
              </TableSortLabel>
            </TableCell>
          ))}
        </TableRow> */}
        <TableRow>
          <TableCell>Employee Number</TableCell>
          <TableCell>Employee First Name</TableCell>
          <TableCell>Employee Last Name</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {data.map((row) => (
          <TableRow key={row.id}>
            {columns.map((column) => (
              <TableCell key={`${row.id}-${column.id}`}>
                {row[column.id]}
              </TableCell>
            ))}
          </TableRow>
        ))}
      </TableBody>
    </Table>

      {/* {data.map(item => (
        <div key={item.empNo}>
          <h2>{item.empFirstName}</h2>
          <p>{item.empLastName}</p>
        </div>
      ))} */}
      <ReactPaginate
        pageCount={Math.ceil(data.length / ITEMS_PER_PAGE)}
        pageRangeDisplayed={5}
        marginPagesDisplayed={2}
        onPageChange={handlePageClick}
        containerClassName={"pagination"}
        activeClassName={"active"}
      />

    </div>
  );
}

export default table;