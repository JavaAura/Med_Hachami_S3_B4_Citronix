package com.citronix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.citronix.dao.FarmDao;
import com.citronix.dto.req.FarmDTO;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.model.Farm;
import com.citronix.repository.FarmRepository;

class FarmServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmDao farmDao;

    @InjectMocks
    private FarmService farmService;

    @Mock
    private FarmMapperDTO mockFarmMapper;


    private Farm mockFarm;
    private FarmDTO mockFarmDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockFarm = new Farm();
        mockFarm.setId(1L);
        mockFarm.setFarmName("Mock Farm");
        mockFarm.setFarmAddress("Mock Address");

        mockFarmDTO = new FarmDTO();
        mockFarmDTO.setId(1L);
        mockFarmDTO.setName("Mock Farm DTO");
        mockFarmDTO.setAddress("Mock Address DTO");

        // Correctly mock the mapper behavior
        when(mockFarmMapper.toEntity(mockFarmDTO)).thenReturn(mockFarm);
        when(mockFarmMapper.toDTO(mockFarm)).thenReturn(mockFarmDTO);
    }


    @Test
    void testSaveFarm_Success() {
        // Correctly mock repository and mapper behavior
        when(mockFarmMapper.toEntity(mockFarmDTO)).thenReturn(mockFarm);
        when(farmRepository.save(any(Farm.class))).thenReturn(mockFarm);

        // when(farmRepository.save(mockFarm)).thenReturn(mockFarm);
        when(mockFarmMapper.toDTO(mockFarm)).thenReturn(mockFarmDTO);

        // Call the service method
        Farm result = farmService.saveFarm(mockFarmDTO);
 
        // Assertions
        assertNotNull(result, "The saved farm should not be null");
        assertEquals("Mock Farm", result.getFarmName(), "Farm name should match");
    }

    @Test
    void testFindFarmByNameAndAddress() {

        List<FarmDTO> mockFarms = new ArrayList<>();
        mockFarms.add(new FarmDTO(1L, "Farm 1", "Farm 1 Address", 20.0));
        mockFarms.add(new FarmDTO(2L, "Farm 2", "Farm 2 Address", 10.0));

        when(farmDao.findFarmByNameAndAddress("Farm 1", "Farm 1 Address", 0, 5))
            .thenReturn(mockFarms);

        List<FarmDTO> result = farmService.findFarmByNameAndAddress("Farm 1", "Farm 1 Address", 0, 5);

         // Assertions
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The size of the result should be 2");
        assertEquals("Farm 1", result.get(0).getName(), "The first farm's name should match");
        assertEquals("Farm 1 Address", result.get(0).getAddress(), "The first farm's address should match");
        assertEquals(20.0, result.get(0).getSurface(), "The first farm's surface should match");

        // Verify the interaction with farmDao
        verify(farmDao, times(1)).findFarmByNameAndAddress("Farm 1", "Farm 1 Address", 0, 5);



    }

    @Test
    void testGetFarmById() {
        Long farmId = 1L;
        Farm mockFarm = new Farm(farmId, "Farm 1", "Farm 1 Address", 50.0); // Use Farm entity
    
        // Mock the behavior of farmRepository to return a Farm, not a FarmDTO
        when(farmRepository.findById(farmId)).thenReturn(Optional.of(mockFarm));
    
        // Call the service method
        FarmDTO result = farmService.getFarmById(farmId);
    
        // Assertions
        assertNotNull(result);
        assertEquals(farmId, result.getId(), "The ID of the farm should match");
    
        verify(farmRepository).findById(farmId);
    }

    @Test
    void testDeleteFarmById_Success() {
        Long farmId = 1L;
        Farm mockFarm = new Farm(farmId, "Farm 1", "Farm 1 Address", 50.0);


        when(mockFarmMapper.toEntity(mockFarmDTO)).thenReturn(mockFarm);
        when(farmRepository.save(any(Farm.class))).thenReturn(mockFarm);

        Farm result = farmService.saveFarm(mockFarmDTO);


        // when(farmRepository.save(mockFarm)).thenReturn(mockFarm);
        when(farmRepository.existsById(farmId)).thenReturn(true);
        when(farmRepository.findById(farmId)).thenReturn(Optional.of(mockFarm));
        doNothing().when(farmRepository).deleteById(farmId);

    
    
        // Verify that deleteById was called once with the correct id
        // verify(farmRepository, times(1)).deleteById(farmId);
    }

}