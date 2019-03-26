USE [CINEMA]

/****** Object:  Table [dbo].[BANNER]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[BANNER](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime2](7) NULL,
	[filename] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[title] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[MOVIE_DETAILS]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[MOVIE_DETAILS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[actors] [varchar](255) NULL,
	[director] [varchar](255) NULL,
	[synopsis] [text] NULL,
	[trailer] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[MOVIES]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[MOVIES](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[classification] [varchar](255) NULL,
	[duration] [int] NOT NULL,
	[filename] [varchar](255) NULL,
	[releaseDate] [datetime2](7) NULL,
	[status] [varchar](255) NULL,
	[title] [varchar](255) NULL,
	[type] [varchar](255) NULL,
	[detail_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_ragi6whancb4l1qs8pkqrkq1f] UNIQUE NONCLUSTERED 
(
	[detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[NEWS]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[NEWS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[publicationDate] [datetime2](7) NULL,
	[detail] [text] NULL,
	[status] [varchar](255) NULL,
	[title] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[PROFILES]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[PROFILES](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UKgip4n22dvlnpx0q7ssa5hac4u] UNIQUE NONCLUSTERED 
(
	[id] ASC,
	[profile] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[SHOWTIMES]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[SHOWTIMES](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime2](7) NULL,
	[price] [float] NOT NULL,
	[room] [varchar](255) NULL,
	[time] [varchar](255) NULL,
	[movie_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[USERS]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

SET ANSI_PADDING ON

CREATE TABLE [dbo].[USERS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


SET ANSI_PADDING OFF

/****** Object:  Table [dbo].[USERS_PROFILES]    Script Date: 21/03/2019 17:29:58 ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[USERS_PROFILES](
	[User_id] [int] NOT NULL,
	[profile_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[User_id] ASC,
	[profile_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


ALTER TABLE [dbo].[MOVIES]  WITH CHECK ADD  CONSTRAINT [FKj6vj1rbic4diyi5388490oqdq] FOREIGN KEY([detail_id])
REFERENCES [dbo].[MOVIE_DETAILS] ([id])

ALTER TABLE [dbo].[MOVIES] CHECK CONSTRAINT [FKj6vj1rbic4diyi5388490oqdq]

ALTER TABLE [dbo].[SHOWTIMES]  WITH CHECK ADD  CONSTRAINT [FKjgtqe2xus31j1c12wytq2110g] FOREIGN KEY([movie_id])
REFERENCES [dbo].[MOVIES] ([id])

ALTER TABLE [dbo].[SHOWTIMES] CHECK CONSTRAINT [FKjgtqe2xus31j1c12wytq2110g]

ALTER TABLE [dbo].[USERS_PROFILES]  WITH CHECK ADD  CONSTRAINT [FKk1lxhgguqayn6rpdj8mstxvmw] FOREIGN KEY([User_id])
REFERENCES [dbo].[USERS] ([id])

ALTER TABLE [dbo].[USERS_PROFILES] CHECK CONSTRAINT [FKk1lxhgguqayn6rpdj8mstxvmw]

ALTER TABLE [dbo].[USERS_PROFILES]  WITH CHECK ADD  CONSTRAINT [FKodjbd7l5aj4g1et425hhelg0d] FOREIGN KEY([profile_id])
REFERENCES [dbo].[PROFILES] ([id])

ALTER TABLE [dbo].[USERS_PROFILES] CHECK CONSTRAINT [FKodjbd7l5aj4g1et425hhelg0d]