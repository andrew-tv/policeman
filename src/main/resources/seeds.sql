DROP TABLE dbo.Clients;
CREATE TABLE dbo.Clients (
                             Id              int             NOT NULL IDENTITY(1,1) PRIMARY KEY,
                             SourceTypeId    int             NOT NULL,
                             SourceId        varchar(150)    DEFAULT NULL,
                             Name            nvarchar(200)   NOT NULL,
                             IsSuspended     tinyint         CHECK (IsSuspended >= 0 AND IsSuspended <= 1) NOT NULL DEFAULT 0,
                             CreatedAt       datetime2       NOT NULL DEFAULT GETDATE(),
                             CreatedBy	    nvarchar(20)    DEFAULT NULL,
                             ModifiedAt      datetime2       NOT NULL DEFAULT GETDATE(),
                             ModifiedBy      nvarchar(20)	DEFAULT NULL,
                             DeletedAt       datetime2       DEFAULT NULL,
                             DeleteBy        nvarchar(20)	DEFAULT NULL
);
INSERT INTO dbo.Clients (SourceTypeId, SourceId, Name, IsSuspended, CreatedBy, ModifiedBy) -- CreatedAt, ModifiedAt, DeletedAt, DeleteBy
VALUES
    (2, '123456', 'Fima', 0, 'usr:1', 'usr:2'),
    (2, '123456', 'Rina', 1, 'usr:3', 'usr:4');